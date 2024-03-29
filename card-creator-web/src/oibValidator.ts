export const isValidOIB = (oib:string): boolean => {
    if (!/^[0-9]{11}$/.test(oib)) 
        return false;

    let a = 10;
    for(const digit of oib.substring(0, 10)) {
        a += parseInt(digit);
        a %= 10;
        if (a === 0) 
            a = 10;
        a *= 2;
        a %= 11;
    }

    let check = 11 - a;
    if (check === 10) 
        check = 0;

    return check === parseInt(oib.substr(10, 1));
}