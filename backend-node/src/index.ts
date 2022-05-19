const express = require('express');
const app = express();
const port = 3000;

import {computeBills} from './compute-bills'

app.use(express.json());

app.get("/", (req: any, res: any) => {
    res.send("Hello World!")
});

app.post("/compute-bills", (req: any, res: any) => {
    res.send(computeBills(req.body.shipping, req.body.items));
});

app.listen(port, () => {
    console.log(`App is listening on port ${port}`);
});