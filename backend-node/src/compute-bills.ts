import { Bill } from "./models/bill";
import { Shipping } from './models/shipping';

export function computeBills(shipping: number, items: Array<Shipping>) {
    let result: Array<Bill> = [];
    
    const grandTotal: number = items.map(item => computeTotal(item.quantity, item.price))
                                .reduce((a, b) => a + b, 0);
    

    const totals = buildMap(items);

    totals.forEach((value: number, key: string) => {
        const shippingForBuyerRounded = rounded(value / grandTotal * shipping);
        
        result.push({
            name: key,
            total: value,
            shipping: shippingForBuyerRounded
        });
    });

    const totalShipping = result.map(r => r.shipping)
                                .reduce((a, b) => a + b, 0);

    if(totalShipping !== shipping && result.length > 0) {
        return cascadeRounding(shipping, result);
    }
    
    return result.sort((a, b) => a.name.localeCompare(b.name));
}

function buildMap(items: Shipping[]) {
    const totals: Map<string, number> = new Map();

    items.forEach(item => {
        if (!totals.has(item.name)) {
            totals.set(item.name, 0);
        }

        if (totals.has(item.name)) {
            totals.set(item.name, totals.get(item.name)! + computeTotal(item.quantity, item.price));
        }
    });

    return totals;
}

function cascadeRounding(shipping: number, bills: Array<Bill>): Array<Bill> {
    let remainingShipping = getRemainingShipping(shipping, bills);
    let billIndex = 0;

    while(remainingShipping !== 0.00) {
        remainingShipping = remainingShipping.toFixed(2) as any - 0.01;
        bills.at(billIndex)!.shipping = bills.at(0)!.shipping + 0.01;
        billIndex = (billIndex === bills.length - 1)?0: billIndex + 1;
    }

    return bills;
}

function rounded(n: number) {
    return Math.round(n * 100) / 100;
}

function computeTotal(quantity: number, price: number): number {
    return quantity * price;
}

function getRemainingShipping(shipping: number, bills: Bill[]) {
    let total = 0;
    
    bills.forEach(bill => {
        total += bill.shipping;
    });

    return rounded(shipping - total);
}
