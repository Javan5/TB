let being = false;


document.addEventListener("click", function (e) {
let src=e.target.getAttribute('src');
let alt=e.target.getAttribute('alt');
let modal = document.getElementById("myModal");
let modalImg = document.getElementById("img01");
let captionText = document.getElementById("caption");

if (e.target.className=='myImg'){
if(!being) {
modal.style.display = "block";
modalImg.src = src;
captionText.innerHTML = alt;
}

}
var span = document.getElementsByClassName("close")[0];
span.onclick = function () {
modal.style.display = "none";
};
}
)