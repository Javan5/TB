let h1 = document.getElementsByTagName('h1')[0];

function playh1(element){
    let str = element.textContent;
    let a = str.slice(1);
    let b = str.slice(0, 1);
    let newstr = a + b;
    element.textContent = newstr;
    return;
};

let si = setInterval(playh1, 500, h1);