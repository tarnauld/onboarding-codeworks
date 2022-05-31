import rounded from './math-utils';

export default class Rounding {
  private result: Array<number> = [];

  public constructor(
    private readonly grandTotal: number,
    readonly values: Array<number>
  ) {
    const total = values.reduce((acc, value) => acc + value, 0);
    this.result = values.map(
      value => rounded((grandTotal * value) / total) - 0.01
    );
  }

  public execute(): Array<number> {
    let remainingShipping = this.getRemain();
    let index = 0;

    while (remainingShipping > 0.0) {
      remainingShipping -= 0.01;
      this.result[index] += 0.01;
      index = index === this.result.length - 1 ? 0 : index + 1;
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
