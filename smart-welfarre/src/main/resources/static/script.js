// Get elements
const toSignup = document.getElementById('to-signup');
const toLogin = document.getElementById('to-login');
const toAdminLogin = document.getElementById('to-admin-login');
const toLoginFromAdmin = document.getElementById('to-login-from-admin');
const loginForm = document.getElementById('login-form');
const signupForm = document.getElementById('signup-form');
const adminLoginForm = document.getElementById('admin-login-form');


toSignup.addEventListener('click', () => {
  loginForm.style.display = 'none';
  signupForm.style.display = 'block';
});

// Switch to Login form from Sign Up
toLogin.addEventListener('click', () => {
  signupForm.style.display = 'none';
  loginForm.style.display = 'block';
});

// Show Admin Login form
toAdminLogin.addEventListener('click', () => {
  loginForm.style.display = 'none';
  adminLoginForm.style.display = 'block';
});

// Switch to Login form from Admin Login
toLoginFromAdmin.addEventListener('click', () => {
  adminLoginForm.style.display = 'none';
  loginForm.style.display = 'block';
});
