const textElement = document.getElementById('text');
const textToWrite = "";
let index = 0;

function writeText() {
    if (index < textToWrite.length) {
        textElement.innerHTML += textToWrite.charAt(index);
        index++;
        setTimeout(writeText, 50); // Adjust the speed of typing here
    }
}

writeText();