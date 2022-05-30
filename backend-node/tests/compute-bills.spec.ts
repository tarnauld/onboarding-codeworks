import BillsProcessor from '../src/compute-bills';
import { Shipping } from '../src/models/shipping';
import dayjs from 'dayjs';

describe('Compute bills', () => {
  it('should return empty list when given empty list', () => {
    const shipping = 1;
    var items: Array<Shipping> = [];
    const billsProcessor = new BillsProcessor(shipping, items);
    const bills = billsProcessor.execute();
    expect(bills).toEqual([]);
  });

  it('should return single bill when given one item list', () => {
    const shipping = 8;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 8,
        price: 2.5,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];
    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 8,
      },
    ]);
  });

  it('should return equal shipping fee when give two items list', () => {
    const shipping = 20;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 10,
        price: 2,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Bernard',
        quantity: 10,
        price: 2,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 10,
      },
      {
        name: 'Bernard',
        total: 20,
        shipping: 10,
      },
    ]);
  });

  it('should return proportional shipping fee when given a two items list', () => {
    const shipping = 10;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 8,
        price: 2.5,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Bernard',
        quantity: 5,
        price: 1,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 8,
      },
      {
        name: 'Bernard',
        total: 5,
        shipping: 2,
      },
    ]);
  });

  it('should return cascade rounded result when shipping fee is equal to one and there are 3 buyers', () => {
    const shipping = 1;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 1,
        price: 1,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Bernard',
        quantity: 1,
        price: 1,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'John',
        quantity: 1,
        price: 1,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    const result = billsProcessor.execute();

    expect(result).toEqual([
      {
        name: 'Alice',
        total: 1,
        shipping: 0.34,
      },
      {
        name: 'Bernard',
        total: 1,
        shipping: 0.33,
      },
      {
        name: 'John',
        total: 1,
        shipping: 0.33,
      },
    ]);

    const totalShippingFee = result.map((r) => r.shipping).reduce((a, b) => a + b);

    expect(totalShippingFee).toEqual(1);
  });

  it('should compute shipping for several items per buyer', () => {
    const shipping = 30;
    const items = [
      { price: 1, quantity: 10, name: 'Bertrand', birthday: dayjs().subtract(7, 'day').toDate() },
      { price: 1, quantity: 10, name: 'Alice', birthday: dayjs().subtract(7, 'day').toDate() },
      { price: 1, quantity: 10, name: 'Alice', birthday: dayjs().subtract(7, 'day').toDate() },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 20,
      },
      {
        name: 'Bertrand',
        total: 10,
        shipping: 10,
      },
    ]);
  });

  it('should compute multiple user', () => {
    const shipping = 100;

    const items = [
      {
        name: 'Bertrand',
        quantity: 1,
        price: 18,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Desmond',
        quantity: 1,
        price: 90,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Alice',
        quantity: 1,
        price: 37.5,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Clara',
        quantity: 1,
        price: 460,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Tim',
        quantity: 1,
        price: 10,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    const result = billsProcessor.execute();

    expect(result).toEqual([
      {
        name: 'Alice',
        total: 37.5,
        shipping: 6.1,
      },
      {
        name: 'Bertrand',
        total: 18,
        shipping: 2.92,
      },
      {
        name: 'Clara',
        total: 460,
        shipping: 74.74,
      },
      {
        name: 'Desmond',
        total: 90,
        shipping: 14.62,
      },
      {
        name: 'Tim',
        total: 10,
        shipping: 1.62,
      },
    ]);

    const totalShippingFee = result.map((r) => r.shipping).reduce((a, b) => a + b);

    expect(totalShippingFee).toEqual(100);
  });

  it('should not produce infinite loop', () => {
    const shipping = 200;

    const items = [
      {
        name: 'Bertrand',
        quantity: 20,
        price: 0.5,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Alice',
        quantity: 25,
        price: 1.5,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Desmond',
        quantity: 50,
        price: 1.8,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Clara',
        quantity: 10,
        price: 2,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Clara',
        quantity: 100,
        price: 4.3,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Bertrand',
        quantity: 1,
        price: 8,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
      {
        name: 'Clara',
        quantity: 10,
        price: 1,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    const result = billsProcessor.execute();

    expect(result).toEqual([
      {
        name: 'Alice',
        total: 37.5,
        shipping: 12.39,
      },
      {
        name: 'Bertrand',
        total: 18,
        shipping: 5.95,
      },
      {
        name: 'Clara',
        total: 460,
        shipping: 151.94,
      },
      {
        name: 'Desmond',
        total: 90,
        shipping: 29.72,
      },
    ]);

    const totalShippingFee = result.map((r) => r.shipping).reduce((a, b) => a + b);

    expect(totalShippingFee).toEqual(200);
  });

  it('should return no shipping fee if it\'s user\s birthday', () => {
    const shipping = 20;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 10,
        price: 2,
        birthday: dayjs().toDate(),
      },
      {
        name: 'Bernard',
        quantity: 10,
        price: 2,
        birthday: dayjs().subtract(7, 'day').toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 0,
      },
      {
        name: 'Bernard',
        total: 20,
        shipping: 20,
      },
    ]);
  });

  it('should return correct shipping fee if it\'s all user\' birthday', () => {
    const shipping = 20;
    const items: Array<Shipping> = [
      {
        name: 'Alice',
        quantity: 10,
        price: 2,
        birthday: dayjs().toDate(),
      },
      {
        name: 'Bernard',
        quantity: 10,
        price: 2,
        birthday: dayjs().toDate(),
      },
    ];

    const billsProcessor = new BillsProcessor(shipping, items);
    expect(billsProcessor.execute()).toEqual([
      {
        name: 'Alice',
        total: 20,
        shipping: 10,
      },
      {
        name: 'Bernard',
        total: 20,
        shipping: 10,
      },
    ]);
  });
});
