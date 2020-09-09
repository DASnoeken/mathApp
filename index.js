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
        function getEncryption(s,order){
            if(s.length==0){
                document.getElementById("response").innerHTML = "What were ye gonne do with that empty string there, mate?";
                return;
            }else if (order.length==0 || order=="order"){
                document.getElementById("response").innerHTML = "You need to insert the order up there!";
                return;
            }
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Encrypt/"+s+"/"+order);
            xhr.send();
        }
        function getDecryption(num){
            if(num.length==0){
                document.getElementById("response").innerHTML = "I would enter a number in that field there, mate!";
                return;
            }
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Decrypt/"+num);
            xhr.send();
        }
        function getUnitConversion(){
            document.getElementById("input").hidden = true;
            document.getElementById("c-to-f").hidden = true;
            document.getElementById("f-to-c").hidden = true;
            document.getElementById("UnitConversionDiv").hidden = false;
            document.getElementById("kg-to-lbs").hidden = true;
            document.getElementById("lbs-to-kg").hidden = true;
            document.getElementById("response").innerHTML = "";
        }
        function backToInput(){
            document.getElementById("input").hidden = false;
            document.getElementById("Gamescreen").hidden = true;
            document.getElementById("UnitConversionDiv").hidden = true;
            document.getElementById("response").innerHTML = "";

        }
        function cTof(){
            document.getElementById("UnitConversionDiv").hidden = true;
            document.getElementById("c-to-f").hidden = false;
            document.getElementById("response").innerHTML = "";
        }
        function fToc(){
            document.getElementById("UnitConversionDiv").hidden = true;
            document.getElementById("f-to-c").hidden = false;
            document.getElementById("response").innerHTML = "";
        }
        function kgToLbs(){
            document.getElementById("kg-to-lbs").hidden = false;
            document.getElementById("UnitConversionDiv").hidden = true;
            document.getElementById("response").innerHTML = "";
        }
        function lbsToKg(){
            document.getElementById("lbs-to-kg").hidden = false;
            document.getElementById("UnitConversionDiv").hidden = true;
            document.getElementById("response").innerHTML = "";
        }
        function cTofCalculate(val){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Units/ctof/"+val);
            xhr.send();
        }
        function fTocCalculate(val){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Units/ftoc/"+val);
            xhr.send();
        }
        function kgToLbsCalculate(val){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Units/kgToLbs/"+val);
            xhr.send();
        }
        function lbsToKgCalculate(val){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState==4){
                    var respons = this.responseText;
                    document.getElementById("response").innerHTML = respons;
                }
            }
            xhr.open("GET","http://localhost:8082/Units/lbsToKg/"+val);
            xhr.send();
        }
        function gameStart(){
            document.getElementById("Gamescreen").hidden = false;
            document.getElementById("input").hidden = true;
        }
        function setDegree(deg){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState == 4){
                    var respons = JSON.parse(this.responseText);
                    console.log(respons);
                    for(var i=0;i<respons.length;i++){
                        console.log(respons[i]);
                        document.getElementById("response").innerHTML += "Point #"+(i+1)+": "+respons[i]+"<br>";
                    }
                }
            }
            xhr.open("GET","http://localhost:8082/game/getpolynomial/"+deg);
            xhr.send();
        }