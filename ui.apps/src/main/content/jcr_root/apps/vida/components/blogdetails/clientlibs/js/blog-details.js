const filterBlogContent = (blogContent,blogId,blogResult) => {
    let isEmpty = false;
    blogContent.forEach(element => {
        if($(element).data('category') === blogId){
            isEmpty = true;
            $(element).show();

        } else{
            $(element).hide();
        }
    });
    isEmpty ? blogResult.hide() : blogResult.show(); 
}
$(document).ready(function(){
    let blogContent = document.querySelectorAll(".vida-blog-card__articlebody");
    let blogNavigation = $(".vida-blog-card__list-item");
    let blogHeader = $(".vida-blog__header");
    let blogResult =  $(".vida-blog-card__results");
    blogResult.hide();
    let url = window.location.hash.split('#')[1];

    if(url){
        filterBlogContent(blogContent, url,blogResult);
        blogHeader.append(" "+ url);
        blogNavigation.each(function(pos, item) {
            if(url === $(item).attr("id")){
                item.classList.add("active");
            }
        })

    } else{
        filterBlogContent(blogContent,blogNavigation[0].id,blogResult);
        blogHeader.append(" "+ blogNavigation[0].id);
        blogNavigation[0].className += " active";
    }
    

    blogNavigation.click(function (event){
        let related = "Blogs related to"
        var currentEle  = document.getElementsByClassName("active");
        currentEle[0].classList.remove("active");
        event.preventDefault();
        let id = $(this).attr("id");
        this.classList.add("active");
        filterBlogContent(blogContent,id,blogResult);
        blogHeader.text(related + " "+ id);
    })

    
})
