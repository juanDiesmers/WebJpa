function toggleForms() {
    const loginForm = document.querySelector('.form-wrapper:not(.register-form)');
    const registerForm = document.querySelector('.register-form');
  
    if (loginForm.style.display === 'none') {
      loginForm.style.display = 'block';
      registerForm.style.display = 'none';
    } else {
      loginForm.style.display = 'none';
      registerForm.style.display = 'block';
    }
  }

