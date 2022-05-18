// search-result

const tabMenu = document.querySelectorAll(".tab-menu li");
const tabContent = document.querySelectorAll(".tab-content > div");

tabMenu.forEach(function (item, i) {
  item.addEventListener("click", function (e) {
    e.preventDefault();
    showContent(i);
  });
});

function showContent(n) {
  tabContent.forEach(function (item) {
    item.style.display = "none";
  });
  tabContent[n].style.display = "block";
}
