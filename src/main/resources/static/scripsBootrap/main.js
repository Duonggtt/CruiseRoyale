(function ($) {
    "use strict";

    var fullHeight = function () {
        $('.js-fullheight').css('height', $(window).height());
        $(window).resize(function () {
            $('.js-fullheight').css('height', $(window).height());
        });
    };
    fullHeight();

    var owl = $('.featured-carousel');

    $('.featured-carousel').owlCarousel({
        animateOut: 'fadeOut',
        center: false,
        items: 1,
        loop: true,
        stagePadding: 0,
        margin: 0,
        smartSpeed: 1500,
        autoplay: false,
        dots: false,
        nav: false,
        navText: ['<span class="icon-keyboard_arrow_left">', '<span class="icon-keyboard_arrow_right">']
    });

    var thumbnailList = $('.thumbnail');

    owl.on('changed.owl.carousel', function (event) {
        var currentImageIndex = event.item.index;
        updateThumbnailList(currentImageIndex);
    });

    function updateThumbnailList(currentIndex) {
        var start = Math.max(0, currentIndex - 2); // Display 5 images, so adjust accordingly
        var end = Math.min(start + 4, owl.find('.item').length - 1);

        $('.thumbnail li').removeClass('active'); // Remove active class from all thumbnails
        thumbnailList.empty(); // Clear the thumbnail list

        for (var i = start; i <= end; i++) {
            var imageUrl = owl.find('.item').eq(i).find('.img').css('background-image').replace('url("', '').replace('")', '');
            thumbnailList.append('<li><a href="#"><img src="' + imageUrl + '" alt="Image" class="img-fluid"></a></li>');
        }

        // Highlight the current thumbnail
        thumbnailList.find('li').eq(currentIndex - start).addClass('active');
    }

    thumbnailList.on('click', 'li', function (e) {
        var index = $(this).index();
        owl.trigger('to.owl.carousel', [index + Math.max(0, owl.find('.item.active').index() - 2), 1500]);
        e.preventDefault();
    });

})(jQuery);
