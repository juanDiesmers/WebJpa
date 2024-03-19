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


  function registrarArrendador() {
    const nombres = document.getElementById('new-firstname').value;
    const apellidos = document.getElementById('new-lastname').value;
    const correo = document.getElementById('new-email').value;
    const telefono = document.getElementById('new-phone').value;
    const contraseña = document.getElementById('new-password').value;
    const tipoUsuario = document.querySelector('input[name="user-type"]:checked').value;
  
    const arrendadorData = {
      nombres: nombres,
      apellidos: apellidos,
      correo: correo,
      telefono: telefono,
      contraseña: contraseña,
      tipoUsuario: tipoUsuario
    };
  
    fetch('/submit', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(arrendadorData),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Arrendador agregado:', data);
      // Aquí puedes realizar acciones adicionales después de agregar el arrendador, como redireccionar a otra página.
      window.location.href = "/grupo12/login.html";
    })
    .catch(error => {
      console.error('Error al agregar arrendador:', error);
    });
  }