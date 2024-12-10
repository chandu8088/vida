
(function (document, $) {
    "use strict";

    // when dialog gets injected
    $(document).on("foundation-contentloaded", function (e) {
        // if there is already an inital value make sure the according target element becomes visible
        checkboxShowHideHandler($(".cq-dialog-checkbox-showhide", e.target));
    });

    $(document).on("change", ".cq-dialog-checkbox-showhide", function (e) {
        checkboxShowHideHandler($(this));
    });

    function checkboxShowHideHandler(el) {
        el.each(function (i, element) {
            if($(element).is("coral-checkbox")) {
                // handle Coral3 base drop-down
                Coral.commons.ready(element, function (component) {
                    showHide(component, element);
                    component.on("change", function () {
                        showHide(component, element);
                    });
                });
            } else {
                // handle Coral2 based drop-down
                var component = $(element).data("checkbox");
                if (component) {
                    showHide(component, element);
                }
            }
        })
    }

    function showHide(component, element) {
        console.log('showing');
        // get the selector to find the target elements. its stored as data-.. attribute
        var target1 = $(element).data("cqDialogCheckboxShowhideTarget1");
        var target2 = $(element).data("cqDialogCheckboxShowhideTarget2");
        var $target1 = $(target1);
        var $target2 = $(target2);


        if (target1) {
            $target1.addClass("hide");
            if (component.checked) {
                $target1.removeClass("hide");
            }
        }
        if (target2) {
            $target2.removeClass("hide");
            if (component.checked) {
                $target2.addClass("hide");
            }
        }
    }
})(document, Granite.$);