
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.documentElement.scrollTop > 200) {
    document.getElementById("myBtn").style.display = 'block';
  } 
  else {
    document.getElementById("myBtn").style.display = 'none';
  }
}

function topFunction() {
  document.documentElement.scrollTop = 0;
}