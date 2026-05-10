
db = db.getSiblingDB('trdDB');

db.assets.insertOne({
    cusip: 'AAPL123',
    fundName: 'Apple Growth Fund',
    launchDate: new Date('2020-01-15'),
    active: true
});

db.assets.insertOne({
    cusip: 'GOOG456',
    fundName: 'Google Technology Fund',
    launchDate: new Date('2019-06-20'),
    active: true
});

db.assets.insertOne({
    cusip: 'MSFT789',
    fundName: 'Microsoft Innovation Fund',
    launchDate: new Date('2021-03-10'),
    active: false
});

db.assets.insertOne({
    cusip: 'AMZN012',
    fundName: 'Amazon Commerce Fund',
    launchDate: new Date('2018-11-05'),
    active: true
});

db.assets.insertOne({
    cusip: 'TSLA345',
    fundName: 'Tesla Energy Fund',
    launchDate: new Date('2022-07-22'),
    active: true
});

// Prices for Apr 20, 2026
db.prices.insertOne({
    cusip: 'AAPL123',
    priceDate: new Date('2026-04-20'),
    currency: 'USD',
    price: 45.67
});

db.prices.insertOne({
    cusip: 'GOOG456',
    priceDate: new Date('2026-04-20'),
    currency: 'USD',
    price: 78.23
});

db.prices.insertOne({
    cusip: 'MSFT789',
    priceDate: new Date('2026-04-20'),
    currency: 'USD',
    price: 89.45
});

db.prices.insertOne({
    cusip: 'AMZN012',
    priceDate: new Date('2026-04-20'),
    currency: 'USD',
    price: 23.89
});

db.prices.insertOne({
    cusip: 'TSLA345',
    priceDate: new Date('2026-04-20'),
    currency: 'USD',
    price: 67.12
});

// Prices for Apr 21, 2026
db.prices.insertOne({
    cusip: 'AAPL123',
    priceDate: new Date('2026-04-21'),
    currency: 'USD',
    price: 47.89
});

db.prices.insertOne({
    cusip: 'GOOG456',
    priceDate: new Date('2026-04-21'),
    currency: 'USD',
    price: 76.34
});

db.prices.insertOne({
    cusip: 'MSFT789',
    priceDate: new Date('2026-04-21'),
    currency: 'USD',
    price: 91.56
});

db.prices.insertOne({
    cusip: 'AMZN012',
    priceDate: new Date('2026-04-21'),
    currency: 'USD',
    price: 25.67
});

db.prices.insertOne({
    cusip: 'TSLA345',
    priceDate: new Date('2026-04-21'),
    currency: 'USD',
    price: 69.78
});

// Prices for Apr 22, 2026
db.prices.insertOne({
    cusip: 'AAPL123',
    priceDate: new Date('2026-04-22'),
    currency: 'USD',
    price: 44.12
});

db.prices.insertOne({
    cusip: 'GOOG456',
    priceDate: new Date('2026-04-22'),
    currency: 'USD',
    price: 79.89
});

db.prices.insertOne({
    cusip: 'MSFT789',
    priceDate: new Date('2026-04-22'),
    currency: 'USD',
    price: 87.23
});

db.prices.insertOne({
    cusip: 'AMZN012',
    priceDate: new Date('2026-04-22'),
    currency: 'USD',
    price: 22.45
});

db.prices.insertOne({
    cusip: 'TSLA345',
    priceDate: new Date('2026-04-22'),
    currency: 'USD',
    price: 65.34
});

// Prices for Apr 23, 2026
db.prices.insertOne({
    cusip: 'AAPL123',
    priceDate: new Date('2026-04-23'),
    currency: 'USD',
    price: 48.56
});

db.prices.insertOne({
    cusip: 'GOOG456',
    priceDate: new Date('2026-04-23'),
    currency: 'USD',
    price: 77.01
});

db.prices.insertOne({
    cusip: 'MSFT789',
    priceDate: new Date('2026-04-23'),
    currency: 'USD',
    price: 92.78
});

db.prices.insertOne({
    cusip: 'AMZN012',
    priceDate: new Date('2026-04-23'),
    currency: 'USD',
    price: 26.89
});

db.prices.insertOne({
    cusip: 'TSLA345',
    priceDate: new Date('2026-04-23'),
    currency: 'USD',
    price: 71.23
});

db.accounts.insertOne({
    accountNumber: 1001234567,
    registeredOrgName: 'Alpha Trading Corporation',
    active: true
});

db.accounts.insertOne({
    accountNumber: 1001234568,
    registeredOrgName: 'Beta Investment Partners',
    active: true
});

db.accounts.insertOne({
    accountNumber: 1001234569,
    registeredOrgName: 'Gamma Securities LLC',
    active: false
});

db.accounts.insertOne({
    accountNumber: 1001234570,
    registeredOrgName: 'Delta Asset Management',
    active: true
});

db.accounts.insertOne({
    accountNumber: 1001234571,
    registeredOrgName: 'Epsilon Financial Services',
    active: true
});

// Positions using AAPL123 and GOOG456 CUSIPs with account numbers 1001234567 and 1001234568
db.positions.insertOne({
    cusip: 'AAPL123',
    accountNumber: 1001234567,
    holding: 150.50,
    asOfDate: new Date()
});

db.positions.insertOne({
    cusip: 'AAPL123',
    accountNumber: 1001234568,
    holding: 75.25,
    asOfDate: new Date()
});

db.positions.insertOne({
    cusip: 'GOOG456',
    accountNumber: 1001234567,
    holding: 200.00,
    asOfDate: new Date()
});

db.positions.insertOne({
    cusip: 'GOOG456',
    accountNumber: 1001234568,
    holding: 125.75,
    asOfDate: new Date()
});