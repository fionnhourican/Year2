let person = {
    name: 'John',
    age: 18,
    address: 'Crott',

    sayHello: function() {
    console.log("Hello my name is " + this.name);
    }
};

person.sayHello();