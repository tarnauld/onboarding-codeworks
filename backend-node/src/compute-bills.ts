import { Bill } from './models/bill';
import { Shipping } from './models/shipping';
import CascadeRounding from './cascade-rounding';

export default class BillsProcessor {
  private readonly result: Array<Bill> = [];

  public constructor(readonly shipping: number, readonly items: Array<Shipping>) {
    this.buildMap().forEach((value: number, key: string) => {
      this.result.push({
        name: key,
        total: value,
        shipping: 0,
      });
    });

    this.result = this.result.sort((a, b) => a.name.localeCompare(b.name));
  }

  public execute() {
    const cascadeRounding = new CascadeRounding(
      this.shipping,
      this.result.map((r) => r.total)
    ).execute();

    this.result.forEach((r, index) => {
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
