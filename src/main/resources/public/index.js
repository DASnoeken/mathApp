function gotoCV() {
    window.location.href = "https://daanscv.herokuapp.com/";
}
function gotoGitHub(){
    window.location.href = "https://github.com/DASnoeken/mathApp";
}
function getHelp(){
    window.location.href = "assets/MathApp_documentation.pdf";
}
function getEncryptionHelp() {
    window.location.href = "assets/Encryption.pdf";
}
function reportBug() {
    window.location.href = "mailto:d.a.snoeken@protonmail.com?Subject=MathApp Bug Report";
}
function getSum(begin, end) {
    if (isNaN(begin) || isNaN(end)) {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/sum/" + begin + "/" + end);
    xhr.send();
}
function factorial(num) {
    if (isNaN(num) || num == "") {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (num > 100000) {
        document.getElementById("response").innerHTML = "Don't exceed n=100 000";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/factorial/" + num);
    xhr.send();
}
function geometric(num) {
    if (isNaN(num) || num == "") {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/series/geometric/" + num);
    xhr.send();
}
function getE(num) {
    if (isNaN(num) || num == "") {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/getNumber/e/" + num);
    xhr.send();
}
function getDer(func) {
    if (func == "") {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/get/derivative/" + func);
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Encrypt/" + s + "/" + order);
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Decrypt/" + num);
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
    document.getElementById("LinAlgScreen").hidden = true;
    document.getElementById("FactorizationDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function inchToCm() {
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("inch-to-cm").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function cmToInch() {
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
function milesToKm() {
    document.getElementById("miles-to-km").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function kmToMiles() {
    document.getElementById("km-to-miles").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function convertCurrencies() {
    document.getElementById("currencies").hidden = false;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function gotoLinAlg() {
    document.getElementById("input").hidden = true;
    document.getElementById("Gamescreen").hidden = true;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("LinAlgScreen").hidden = false;
    document.getElementById("LinAlgOperationsScreen").hidden = true;
    document.getElementById("response").innerHTML = "Response area";
}
function gotoLinAlgOperations() {
    document.getElementById("input").hidden = true;
    document.getElementById("Gamescreen").hidden = true;
    document.getElementById("UnitConversionDiv").hidden = true;
    document.getElementById("LinAlgScreen").hidden = true;
    document.getElementById("LinAlgOperationsScreen").hidden = false;
    document.getElementById("response").innerHTML = "Response area";
}
function toFactorizations() {
    document.getElementById("input").hidden = true;
    document.getElementById("FactorizationDiv").hidden = false;
}
function inchToCmCalculate(foot, inch) {
    if (isNaN(foot) || isNaN(inch)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (foot < 0 || inch < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/inchToCm/" + foot + "/" + inch);
    xhr.send();
}
function cmToInchCalculate(val) {
    if (isNaN(val)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (val < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/cmToInch/" + val);
    xhr.send();
}
function cTofCalculate(val) {
    if (isNaN(val)) {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/ctof/" + val);
    xhr.send();
}
function fTocCalculate(val) {
    if (isNaN(val)) {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/ftoc/" + val);
    xhr.send();
}
function kgToLbsCalculate(val) {
    if (isNaN(val)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (val < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/kgToLbs/" + val);
    xhr.send();
}
function lbsToKgCalculate(val) {
    if (isNaN(val)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (val < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/lbsToKg/" + val);
    xhr.send();
}
function milesToKmCalculate(val) {
    if (isNaN(val)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (val < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/milesToKm/" + val);
    xhr.send();
}
function kmToMilesCalculate(val) {
    if (isNaN(val)) {
        document.getElementById("response").innerHTML = "Not a Number";
        return;
    } else if (val < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Units/kmToMiles/" + val);
    xhr.send();
}
function gameStart() {
    document.getElementById("Gamescreen").hidden = false;
    document.getElementById("input").hidden = true;
}
function setDegree(deg) {
    document.getElementById("response").innerHTML = "Response area";
    if (isNaN(deg) || deg == "") {
        document.getElementById("response").innerHTML = "This should be an integer!";
        return;
    } else if (deg > 10) {
        document.getElementById("response").innerHTML = "Use degree < 10";
        return;
    } else if (deg < 0) {
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
    xhr.open("GET", "https://daansmathapp.herokuapp.com/game/getpolynomial/" + deg);
    xhr.send();
}
function submitGame(deg) {
    var xhr = new XMLHttpRequest();
    var arr = new Array();
    for (var i = 0; i <= deg; i++) {
        if (!isNaN(document.getElementById("coef" + i).value)) {
            arr.push(document.getElementById("coef" + i).value);
        } else {
            document.getElementById("response").innerHTML = "You can only use numbers!";
            return;
        }
    }
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            getAnswer();
        }
    }
    xhr.open("POST", "https://daansmathapp.herokuapp.com/game/submitAnswer");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(arr));

}
function getAnswer() {
    var xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function () {
        if (this.readyState == 4) {
            var respons = this.responseText;
            document.getElementById("response").innerHTML = respons;
        }
    }
    xhr2.open("GET", "https://daansmathapp.herokuapp.com/game/checkAnswer");
    xhr2.send();
    document.getElementById("setDegreeButton").disabled = false;
}
function calculator(sum) {
    var radios = document.getElementsByName('trig');
    var trigState;
    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            trigState = radios[i].value;
            break;
        }
    }
    if (sum == "") {
        document.getElementById("response").innerHTML = "ERROR: Need Input!"
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            calculatorAnswer();
        }
    }
    xhr.open("POST", "https://daansmathapp.herokuapp.com/calculator/setCalculation/?trigState=" + trigState);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(sum);
}
function calculatorAnswer() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/calculator/getAnswer");
    xhr.send();
}
function currConversion(from, to, value) {
    if (isNaN(value) || value == "") {
        document.getElementById("response").innerHTML = "ERROR: Invalid Input!";
        return;
    }
    if (from == to) {
        document.getElementById("response").innerHTML = "ERROR: Convert to different currency!";
        return;
    } else if (value < 0) {
        document.getElementById("response").innerHTML = "Positive numbers only!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 1) {
            document.getElementById("convertCurrencyButton").disabled = true;
            document.getElementById("ReEnableButton").hidden = false;
        }
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/currency/" + from + "/" + to + "/" + value);
    xhr.send();
}
function reEnable() {
    setTimeout(() => {
        document.getElementById("convertCurrencyButton").disabled = false;
    }, 1000);
    document.getElementById("ReEnableButton").hidden = true;
}
function switchCurrencies() {
    var tmp = document.getElementById("fromCurrency").value;
    document.getElementById("fromCurrency").value = document.getElementById("toCurrency").value;
    document.getElementById("toCurrency").value = tmp;
}
function sendMatrix(matrixInput) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            checkMatrixErrors();
        }
    }
    xhr.open("POST", "https://daansmathapp.herokuapp.com//LinAlg/makeMatrix");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(matrixInput);
}
function checkMatrixErrors() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.responseText != "") {
                document.getElementById("response").innerHTML = this.responseText;
            } else {
                printAllMatrices();
            }
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/getErrorMessage");
    xhr.send();
}
function printMatrix(id) {
    if (isNaN(id)) {
        document.getElementById("response").innerHTML = "ID has to be a number!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;

        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/getMatrixString/" + id)
    xhr.send();
}
function printAllMatrices() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/getAllMatrixStrings")
    xhr.send();
}
function deleteMatrixById(ID) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = "Matrix with ID " + ID + " removed!";
        }
    }
    xhr.open("DELETE", "https://daansmathapp.herokuapp.com/LinAlg/deleteById/" + ID);
    xhr.send();
}
function clearAllMatrices() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = "Memory cleared!";
        }
    }
    xhr.open("DELETE", "https://daansmathapp.herokuapp.com/LinAlg/clearMatrices");
    xhr.send();
}
function addMatrix(ID1, ID2) {
    if (isNaN(ID1) || isNaN(ID2)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID's!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/add/" + ID1 + "/" + ID2);
    xhr.send();
}
function multiplyMatrix(ID1, ID2) {
    if (isNaN(ID1) || isNaN(ID2)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID's!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/multiply/" + ID1 + "/" + ID2);
    xhr.send();
}
function matrixPower(ID, pow) {
    if (isNaN(ID) || isNaN(pow)) {
        document.getElementById("response").innerHTML = "Only use numbers as input!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
            MathJax.typeset();
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/Power/" + ID + "?power=" + pow);
    xhr.send();
}
function transposeMatrix(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/transpose/" + ID);
    xhr.send();
}
function subtractMatrix(ID1, ID2) {
    if (isNaN(ID1) || isNaN(ID2)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID's!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/subtract/" + ID1 + "/" + ID2);
    xhr.send();
}
function ref(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/ref/" + ID);
    xhr.send();
}
function rref(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/rref/" + ID);
    xhr.send();
}
function scaleMatrix(ID, Scalar) {
    if (isNaN(ID) || isNaN(Scalar)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID and scalar!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/scale/" + ID + "/?scalar=" + Scalar);
    xhr.send();
}
function determinant(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/determinant/" + ID);
    xhr.send();
}
function trace(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/Trace/" + ID);
    xhr.send();
}
function inverse(ID) {
    if (isNaN(ID)) {
        document.getElementById("response").innerHTML = "Only use numbers for the ID!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/LinAlg/Operations/inverse/" + ID);
    xhr.send();
}
function factorize(num) {
    if (isNaN(num)) {
        document.getElementById("response").innerHTML = "Only numbers allowed!";
        return;
    }
    if(num<=0){
        document.getElementById("response").innerHTML = "Only integers > 0 are allowed!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
            MathJax.typeset();
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Factorize/getPrimeFactorization/" + num);
    xhr.send();
}
function binomial(pow) {
    if (isNaN(pow)) {
        document.getElementById("response").innerHTML = "Only numbers allowed!";
        return;
    }
    pow = Math.floor(pow);
    if(pow<=0){
        document.getElementById("response").innerHTML = "Only integers > 0 are allowed!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
            MathJax.typeset();
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Binomial/get/" + pow);
    xhr.send();
}
function isPrime(num){
    if (isNaN(num)) {
        document.getElementById("response").innerHTML = "Only numbers allowed!";
        return;
    }
    if(num<=0){
        document.getElementById("response").innerHTML = "Only integers > 0 are allowed!";
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            document.getElementById("response").innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "https://daansmathapp.herokuapp.com/Factorize/isPrime/" + num);
    xhr.send();
}