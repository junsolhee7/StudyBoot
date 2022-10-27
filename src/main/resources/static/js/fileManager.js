console.log("fileManager");

//파일의 갯수를 지정하는 변수
let count = 0;

$("#fileAdd").click(function(){
    if(count<5){
        let r = '<div class="mb-3">';
        r = r + '<label for="contents" class="form-label">File</label>';
        r = r + '<input type="file" name="files">';
        r = r + '<button type="button" class="del">X</button>';
        r = r + '</div>';
    
        $("#fileAddResult").append(r);
        count++;
    }else{
        alert("최대 5개만 가능...")
    }
});

// $(".del").click(function(){
//     console.log("deletee");
// })
//안먹는 이유 = 만들어지기전에 스크립트 (위임 필요)

$("#fileAddResult").on("click",".del",function(){
    console.log("Delete");
    $(this).parent().remove();
    count--;
})

/// 글수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB,HDD에 파일 삭제
    let check = confirm("삭제 됩니다... 복구 불가");
    console.log("Before : "+$(this));
    const btn = $(this);

    if(check){
        //      post
        //      /qna/fileDelete
        //      파라미터 : fileNum
        let fileNum = $(this).attr("data-file-num");
        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("Result : ",result);
                console.log("Atert Result This : ",$(this));
                $(btn).parent().remove();
            },
            error:function(){
                console.log("Error 발생");
            }
        })

    }

});
