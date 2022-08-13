(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 100);
    };
    spinner();
    
    
    // Initiate the wowjs
    var wow = new WOW(
        {
            boxClass:     'wow',      // default
            animateClass: 'animated', // default
            offset:       0,          // default
            mobile:       false,       // default
            live:         true        // default
        }
    )
    wow.init();


    // Fixed Navbar
    // $('.fixed-top').css('top', $('.top-bar').height());
    // $(window).scroll(function () {
    //     if ($(this).scrollTop()) {
    //         $('.fixed-top').addClass('bg-white').css('top', 0);
    //     } else {
    //         $('.fixed-top').removeClass('bg-white').css('top', $('.top-bar').height());
    //     }
    // });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeIn');
        $('.back-to-top').fadeOut('slow');
        return false;
    });


    // Header carousel
    $(".header-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        loop: true,
        nav: false,
        dots: true,
        items: 1,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ]
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        margin: 25,
        loop: true,
        center: true,
        dots: false,
        nav: true,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ],
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });

    //set link for rows of table


    //line-clamp-2
    // let clampObject = $('.js-line-clamp-2')
    // for (const clampObjectElement of clampObject) {
    //     $clamp(clampObjectElement, {clamp: 2})
    // }
    
})(jQuery);


