// small JS for future actions
console.log("Interfaz Lockers cargada");
document.addEventListener("DOMContentLoaded", () => {
  const alerts = document.querySelectorAll(".alert");
  alerts.forEach(alert => {
    setTimeout(() => {
      alert.classList.remove("show");
      alert.classList.add("fade");
    }, 4000);
  });
});
