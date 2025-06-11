// Auto-inicialización del sidebar
(function() {
    function autoInitSidebar() {
        if (window.initSidebar && typeof window.initSidebar === 'function') {
            window.initSidebar();
            console.log('Sidebar auto-inicializado');
        } else {
            // Reintentar después de un momento
            setTimeout(autoInitSidebar, 200);
        }
    }
    
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', autoInitSidebar);
    } else {
        autoInitSidebar();
    }
})();