//set height for header-spacer
let headerJS = document.getElementById('js-header-height');

let headerSpacers = document.getElementsByClassName("header-spacer");

for (const headerSpacer of headerSpacers) {
    headerSpacer.setAttribute("style", "height:" + headerJS.clientHeight + "px");
}
// document.getElementById("sidebar").setAttribute("style", "height:calc(100vh - " + headerHeight.clientHeight + "px)")
document.getElementsByClassName("sidebar-content")[0].setAttribute("style", "height:calc(100vh - " + headerJS.clientHeight + "px)");
