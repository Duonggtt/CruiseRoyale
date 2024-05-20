<!-- script.js -->
document.addEventListener('DOMContentLoaded', function () {
    // Function to load Navbar and Footer from thong-tin-du-thuyen.html
    function loadNavbarAndFooter() {
        fetch('index.html')
            .then(response => response.text())
            .then(data => {
                    const navbar = data.match(/<nav(.*?)<\/nav>/s)[0];
                    const footer = data.match(/<footer(.*?)<\/footer>/s)[0];

                    // Kiểm tra xem Navbar và Footer đã được thêm vào trang chưa
                    if (!document.querySelector('.navbar') && !document.querySelector('footer')) {
                        // Insert Navbar và Footer vào trang
                        document.body.insertAdjacentHTML('afterbegin', navbar);
                        document.body.insertAdjacentHTML('beforeend', footer);
                    }
                }
            )
        ;
    }

    // Load Navbar và Footer on each page
    loadNavbarAndFooter();

    // Additional JavaScript code specific to tim_du_thuyen.html
    if (document.body.classList.contains('tim_du_thuyen')) {
        // Your code for tim_du_thuyen.html
    }

    // Additional JavaScript code specific to tim_ve_may_bay.html
    if (document.body.classList.contains('tim_ve_may_bay')) {
        // Your code for tim_ve_may_bay.html
    }

    // Additional JavaScript code specific to Doanh_Nghiep.html
    if (document.body.classList.contains('doanh_nghiep')) {
        // Your code for Doanh_Nghiep.html
    }

    // Additional JavaScript code specific to Blog.html
    if (document.body.classList.contains('blog')) {
        // Your code for Blog.html
    }
    if (document.body.classList.contains('lien_he')) {

    }
});

