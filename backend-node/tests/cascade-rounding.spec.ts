import CascadeRounding from '../src/cascade-rounding';

describe('Cascade rounding', () => {
  it('should return empty list when given empty list', () => {
    const cascadeRounding = new CascadeRounding(100, []);
    const result = cascadeRounding.execute();
    expect(result).toEqual([]);
  });

  it('should return single bill when given one item list', () => {
    const shipping = 8;
    const items: Array<number> = [8];
    const billsProcessor = new CascadeRounding(shipping, items);
    expect(billsProcessor.execute()).toEqual([8]);
  });

  it('should return equal shipping fee when give two items list', () => {
    const shipping = 20;
    const items: Array<number> = [20, 20];

    const billsProcessor = new CascadeRounding(shipping, items);
    expect(billsProcessor.execute()).toEqual([10, 10]);
  });

  it('should return proportional shipping fee when give two items list', () => {
    const shipping = 30;
    const items: Array<number> = [10, 20];

    const billsProcessor = new CascadeRounding(shipping, items);
    expect(billsProcessor.execute()).toEqual([10, 20]);
  });

  it('should return equal shipping fee', () => {
    const shipping = 10;
    const items: Array<number> = [10, 10, 10];

    const billsProcessor = new CascadeRounding(shipping, items);
    expect(billsProcessor.execute()).toEqual([3.34, 3.33, 3.33]);
  });
});
