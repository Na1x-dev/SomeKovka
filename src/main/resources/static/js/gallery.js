let imageContainer = document.querySelector('.image-container');
let modes = ['swing', 'benches', 'tables'];
let upButtons = [].slice.call(document.getElementsByClassName('up-button'));
let sideButtons = [].slice.call(document.getElementsByClassName('side-button'));
let imageCounter = 0;
let mode = 0;
let img = new Image();
let isReadyToNextImage = false;

function loadImage() {
    img.src = '/sources/gallery/' + modes[mode] + '/' + (imageCounter+1) + '.png';
    imageContainer.src = '/sources/gallery/' + modes[mode] + '/' + imageCounter + '.png';
}

function setHeaderButtonsOnClick() {
    headerButtons.map(function (headerButton, index) {
        headerButton.onclick = function () {
            if (counter != index) {
                counter = index;
                headerButtonBottomBorders[index].style = headerButtonBottomBorderAnimReverse;
                displayPage();
            }
        }
    });
}

function onSideButtonClick() {
    sideButtons.map(function (sideButton, index) {
        sideButton.onclick = function () {
            if (index == 0 && imageCounter > 0) {
                imageCounter--;
            }
            else if (index == 1 && isReadyToNextImage) {
                imageCounter++;
            }
            loadImage();
        }
    });

}

function onUpButtonClick() {
    upButtons.map(function (upButton, index) {
        upButton.onclick = function () {
            if (mode != index) {
                mode = index;
                imageCounter = 0;
                loadImage();
            }
        }
    });

}

function main() {
    img.onload = function () { isReadyToNextImage = true;};
    img.onerror = function () {isReadyToNextImage = false;};
    loadImage();
    onUpButtonClick();
    onSideButtonClick();
}

main();