// login

let id = $('#userEmail');
let pw = $('#userPw');
let btn = $('#btn');

$(btn).on('click', function () {
  if ($(id).val() == '') {
    $(id).next('label').addClass('warning');
    setTimeout(function () {
      $('label').removeClass('warning');
    }, 1500);
  } else if ($(pw).val() == '') {
    $(pw).next('label').addClass('warning');
    setTimeout(function () {
      $('label').removeClass('warning');
    }, 1500);
  }
});
