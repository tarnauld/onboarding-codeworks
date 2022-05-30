/* eslint-disable no-useless-constructor */
/* eslint-disable no-empty-function */

import { Bill } from './models/bill';
import { Shipping } from './models/shipping';
import CascadeRounding from './cascade-rounding';
import BirthdayExclusionProcessor from './birthday-exclusion';

export default class BillsProcessor {
  private result: Array<Bill> = [];

  public constructor(readonly shipping: number, readonly items: Array<Shipping>) {}

  public execute() {
    this.buildMap().forEach((value: number, key: string) => {
      this.result.push({
        name: key,
        total: value,
        shipping: 0,
      });
    });

    this.result = this.result.sort((a, b) => a.name.localeCompare(b.name));

    const birthdays = BirthdayExclusionProcessor.execute(
      this.items.map((item) => ({ name: item.name, birthday: item.birthday }))
    );

    const subResult =
      [...birthdays].filter(([k, v]) => !v).length === 0
        ? this.result
        : this.result.filter((r) => !birthdays.get(r.name));

    const cascadeRounding = new CascadeRounding(
      this.shipping,
      subResult.map((r) => r.total)
    ).execute();

    subResult.forEach((r, index) => {
      r.shipping = cascadeRounding[index];
    });

    return this.result;
  }

  private buildMap() {
    const totals: Map<string, number> = new Map();

    this.items.forEach((item) => {
      if (!totals.has(item.name)) {
        totals.set(item.name, 0);
      }

      totals.set(item.name, totals.get(item.name)! + item.quantity * item.price);
    });

    return totals;
  }
}
