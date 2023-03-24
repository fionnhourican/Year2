function printer(x) {
    return console.log(x);
}

function add(p1, p2) {
    x = p1 + p2;
    return printer(x);
}

(function() {
    add(4, 5);
})();