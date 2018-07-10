document.querySelector('button#open-modal').addEventListener('click', function (event) {
  event.preventDefault();
  var modal = document.getElementById('create-modal');
  var html = document.querySelector('html');
  modal.classList.add('is-active');
  html.classList.add('is-clipped');

  modal.querySelector('.modal-background').addEventListener('click', function (e) {
    e.preventDefault();
    modal.classList.remove('is-active');
    html.classList.remove('is-clipped');
    document.getElementById("path").value = "";
  });
});

document.querySelector('button#open-modal-1').addEventListener('click', function (event) {
  event.preventDefault();
  var modal = document.getElementById('delete-modal');
  var html = document.querySelector('html');
  modal.classList.add('is-active');
  html.classList.add('is-clipped');

  modal.querySelector('.modal-background').addEventListener('click', function (e) {
    e.preventDefault();
    modal.classList.remove('is-active');
    html.classList.remove('is-clipped');
  });
});

function createModalClose() {
  document.getElementById("path").value = "";
  var modal = document.getElementById('create-modal');
  var html = document.querySelector('html');
  modal.classList.remove('is-active');
  html.classList.remove('is-clipped');
}

function deleteModalClose() {
  var modal = document.getElementById('delete-modal');
  var html = document.querySelector('html');
  modal.classList.remove('is-active');
  html.classList.remove('is-clipped');
}