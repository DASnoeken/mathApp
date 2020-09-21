function getSum(begin, end) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/sum/" + begin + "/" + end);
    xhr.send();
}
function factorial(num) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/factorial/" + num);
    xhr.send();
}
function geometric(num) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/series/geometric/" + num);
    xhr.send();
}
function getE(num) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/getNumber/e/" + num);
    xhr.send();
}
function getDer(func) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = "Original: " + func + "<br>";
            document.getElementById("response").innerHTML += "Derivative: " + respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/get/derivative/" + func);
    xhr.send();
}
function getEncryption(s, order) {
    if (s.length == 0) {
        document.getElementById("response").innerHTML = "What were ye gonne do with that empty string there, mate?";
        return;
    } else if (order.length == 0 || order == "order") {
        document.getElementById("response").innerHTML = "You need to insert the order up there!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Encrypt/" + s + "/" + order);
    xhr.send();
}
function getDecryption(num) {
    if (num.length == 0) {
        document.getElementById("response").innerHTML = "I would enter a number in that field there, mate!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Decrypt/" + num);
    xhr.send();
}
function getUnitConversion() {
    document.getElementById("input").hidden = true;
    document.getElementById("c-to-f").hidden = true;
    document.getElementById("f-to-c").hidden = true;
    document.getElementById("UnitConversionDiv").hidden = false;
    document.getElementById("kg-to-lbs").hidden = true;
    document.getElementById("lbs-to-kg").hidden = true;
    document.getElementById("miles-to-km").hidden = true;
    document.getElementById("km-to-miles").hidden = true;
    document.getElementById("eur-to-usd").hidden = true;
    document.getElementById("currencies").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function backToInput() {
    document.getElementById("input").hidden = false;
    document.getElementById("Gamescreen").hidden = true;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function cTof() {
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("c-to-f").hidden = false;
    document.getElementById("response").innerHTML = "";
}
function fToc() {
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("f-to-c").hidden = false;
    document.getElementById("response").innerHTML = "";
}
function kgToLbs() {
    document.getElementById("kg-to-lbs").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function lbsToKg() {
    document.getElementById("lbs-to-kg").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function milesToKm(){
    document.getElementById("miles-to-km").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function kmToMiles(){
    document.getElementById("km-to-miles").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function euroToUSDollars(){
    document.getElementById("eur-to-usd").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function convertCurrencies(){
    document.getElementById("currencies").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "";
}
function cTofCalculate(val) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/ctof/" + val);
    xhr.send();
}
function fTocCalculate(val) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/ftoc/" + val);
    xhr.send();
}
function kgToLbsCalculate(val) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/kgToLbs/" + val);
    xhr.send();
}
function lbsToKgCalculate(val) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/lbsToKg/" + val);
    xhr.send();
}
function milesToKmCalculate(val) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/milesToKm/" + val);
    xhr.send();
}
function kmToMilesCalculate(val){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/kmToMiles/" + val);
    xhr.send();
}
function gameStart() {
    document.getElementById("Gamescreen").hidden = false;
    document.getElementById("input").hidden = true;
}
function setDegree(deg) {
    document.getElementById("response").innerHTML = "";
    if (isNaN(deg)) {
        document.getElementById("response").innerHTML = "This should be an integer!";
        return;
    } else if (deg > 10) {
        document.getElementById("response").innerHTML = "Use degree < 10";
        return;
    }
    document.getElementById("setDegreeButton").disabled = true;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = JSON.parse(this.responseText);
            document.getElementById("Gamescreen2").innerHTML = "<br><br>Given the following points:<br>";
            for (var i = 0; i < respons.length; i++) {
                document.getElementById("Gamescreen2").innerHTML += "Point #" + (i + 1) + ": " + respons[i] + "<br>";
            }
            document.getElementById("Gamescreen2").innerHTML += "Calculate the polynomial coefficients that fits them exactly.<br>Give your coefficients and submit<br>";
            for (var i = 0; i < respons.length; i++) {
                if (i == 0) {
                    document.getElementById("Gamescreen2").innerHTML += "<input type=\"text\" id=\"coef" + i + "\">";
                } else if (i == 1) {
                    document.getElementById("Gamescreen2").innerHTML += "<input type=\"text\" id=\"coef" + i + "\"> x";
                }
                else {
                    document.getElementById("Gamescreen2").innerHTML += "<input type=\"text\" id=\"coef" + i + "\"> x^" + i;
                }
                if (i < respons.length - 1) {
                    document.getElementById("Gamescreen2").innerHTML += " + ";
                }
            }
            document.getElementById("Gamescreen2").innerHTML += "   <button id=\"submitGame\" onclick=\"submitGame(degreeInput.value)\">Submit</button>";
        }
    }
    xhr.open("GET", "http://localhost:8082/game/getpolynomial/" + deg);
    xhr.send();
}
function submitGame(deg) {
    var xhr = new XMLHttpRequest();
    var arr = new Array();
    for (var i = 0; i <= deg; i++) {
        if(!isNaN(document.getElementById("coef" + i).value)){   
            arr.push(document.getElementById("coef" + i).value);
        }else{
            document.getElementById("response").innerHTML = "You can only use numbers!";
            return;
        }
    }
    xhr.onreadystatechange = function(){
        if(this.readyState == 4){
            getAnswer();
        }
    }
    xhr.open("POST", "http://localhost:8082/game/submitAnswer");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(arr));
    
}
function getAnswer(){
    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr2.open("GET", "http://localhost:8082/game/checkAnswer");
    xhr2.send();
    document.getElementById("setDegreeButton").disabled = false;
}
function calculator(sum){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState==4){
            calculatorAnswer();
        }
    }
    xhr.open("POST","http://localhost:8082/calculator/setCalculation");
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send(sum);
}
function calculatorAnswer(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState==4){
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET","http://localhost:8082/calculator/getAnswer");
    xhr.send();
}
function webpage(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4){
            document.getElementById("response").innerHTML = this.responseText;
            console.log(this.responseText);
        }
    }
    xhr.open("GET","http://localhost:8082/web/test");
    xhr.send();
}
function currConversion(from,to,value){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState==4){
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET","http://localhost:8082/currency/"+from+"/"+to+"/"+value);
    xhr.send();
}