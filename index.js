function getSum(begin, end) {
    if(isNaN(begin) || isNaN(end)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(num) || num==""){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(num) || num==""){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(num) || num==""){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
    num = Math.floor(num);
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
    if(func==""){
        document.getElementById("response").innerHTML = "Needs input";
        return;
    }
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
    }
    if (order.length == 0 || order == "order") {
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
    document.getElementById("currencies").hidden = true;
    document.getElementById("cm-to-inch").hidden = true;
    document.getElementById("inch-to-cm").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function backToInput() {
    document.getElementById("input").hidden = false;
    document.getElementById("Gamescreen").hidden = true;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function inchToCm(){
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("inch-to-cm").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function cmToInch(){
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("cm-to-inch").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function cTof() {
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("c-to-f").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function fToc() {
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("f-to-c").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function kgToLbs() {
    document.getElementById("kg-to-lbs").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function lbsToKg() {
    document.getElementById("lbs-to-kg").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function milesToKm(){
    document.getElementById("miles-to-km").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function kmToMiles(){
    document.getElementById("km-to-miles").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function convertCurrencies(){
    document.getElementById("currencies").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function inchToCmCalculate(foot, inch){
    if(isNaN(foot) || isNaN(inch)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState==4){
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET","http://localhost:8082/Units/inchToCm/"+foot+"/"+inch);
    xhr.send();
}
function cmToInchCalculate(val){
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "http://localhost:8082/Units/cmToInch/" + val);
    xhr.send();
}
function cTofCalculate(val) {
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    if(isNaN(val)){
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    }
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
    document.getElementById("response").innerHTML = "Response area";
    if (isNaN(deg) || deg=="") {
        document.getElementById("response").innerHTML = "This should be an integer!";
        return;
    } else if (deg > 10) {
        document.getElementById("response").innerHTML = "Use degree < 10";
        return;
    } else if (deg < 0){
        document.getElementById("response").innerHTML = "Degree should be > 0";
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
            document.getElementById("Gamescreen2").innerHTML += "   <button class=\"buttons\" id=\"submitGame\" onclick=\"submitGame(degreeInput.value)\">Submit</button>";
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
    if(sum==""){
        document.getElementById("response").innerHTML = "ERROR: Need Input!"
        return;
    }
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
function currConversion(from,to,value){
    if(isNaN(value) || value==""){
        document.getElementById("response").innerHTML = "ERROR: Invalid Input!";
        return;
    }
    if(from == to){
        document.getElementById("response").innerHTML = "ERROR: Convert to different currency!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState==1){
            document.getElementById("convertCurrencyButton").disabled = true;
            document.getElementById("ReEnableButton").hidden = false;
        }
        if(this.readyState==4){
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET","http://localhost:8082/currency/"+from+"/"+to+"/"+value);
    xhr.send();
}
function reEnable(){
    setTimeout(() => {
        document.getElementById("convertCurrencyButton").disabled = false;
    }, 1000);
    document.getElementById("ReEnableButton").hidden = true;
}
function switchCurrencies(){
    var tmp = document.getElementById("fromCurrency").value;
    document.getElementById("fromCurrency").value = document.getElementById("toCurrency").value;
    document.getElementById("toCurrency").value = tmp;
}