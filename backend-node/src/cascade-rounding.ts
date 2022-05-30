import rounded from './math-utils';

export default class CascadeRounding {
  private result: Array<number> = [];

  public constructor(private readonly grandTotal: number, readonly values: Array<number>) {
    const t = values.reduce((acc, value) => acc + value, 0);
    values.forEach((value) => {
      this.result.push(rounded((grandTotal * value) / t) - 0.01);
    });
  }

  public execute(): Array<number> {
    let remainingShipping = this.getRemain();
    let index = 0;

    while (remainingShipping > 0.0) {
      remainingShipping -= 0.01;
      this.result[index] += 0.01;
      index = (index === this.result.length - 1) ? 0 : index + 1;
    }

    return this.result;
  }

  private getRemain() {
    let total = 0.0;

    this.result.forEach((value) => {
      total += value;
    });

    return this.result.length > 0 ? rounded(this.grandTotal - total) : 0.0;
  }
}
