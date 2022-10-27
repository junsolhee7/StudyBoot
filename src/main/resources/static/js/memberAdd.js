// .class #id
console.log("memberAdd");

$("#all").click(function(e){
    let ch = $(this).prop("checked");
    $(".check").prop("checked",ch);
})

$(".check").click(function(){
    let flag = true;
    $(".check").each(function(idx,item){

        let ch = $(item).prop("checked");
        if(!ch){
            flag=false;
        }
        console.log(ch);
    })
    $("#all").prop("checked",flag);    
})
//id,pw,pwEquals,name,email
let results = [false,false,false,false,false];

//ID Check
$("#inputId").blur(function(){
    let id = $("#inputId").val();
    let result = nullCheck(id, $("#inputIdResult"),"ID");
    results[0]=result;

    //단 id가 비어있지 않을 때
    $.get("./idCheck?id="+id,function(data){
        console.log("Data : "+data);
        if(data=='0'){
            $("#inputIdResult").html("사용가능한 ID 입니다.");
            result[0]=true;
        }else{
            $("#inputIdResult").html("이미 사용중인 ID 입니다.")
            result[0]=false;
        }
    })
})

//pw Check on 사용
$("#inputPw").on({
    blur:function(){
        let result = nullCheck($("#inputPw").val(), $("#inputPwResult"), "PW" );
        results[1]=result;
        },
    change:function(){
        $("#inputPwCheck").val("");
        results[2]=false;
        $("#inputPwCheckResult").html("");
    }
})

console.log("test5");

//pw Equal Check
$("#inputPwCheck").blur(function(){
    let result = equals($("#inputPw").val(),$("#inputPwCheck").val());
    
    if(result){
        if($("#inputPw").val()==null || $("#inputPw").val()==''){
            $("#inputPwCheckResult").html("PW는 필수입니다.");
        }else{
            $("#inputPwCheckResult").html("PW가 같습니다.");
        }
    }else{
        $("#inputPwCheckResult").html("PW가 다릅니다.");
    }
    results[2]=result;
})

//name check
$("#inputName").blur(function(){
    let result = nullCheck($("#inputName").val(), $("#inputNameResult"), "name");
    results[3]=result;
})
//email check
$("#inputEmail").blur(function(){
    let result = nullCheck($("#inputEmail").val(), $("#inputEmailResult"),"Email")
    results[4]=result;
})

//join results
$("#joinButton").click(function(){

    if(results.includes(false)){
        console.log("필수 입력은 다 입력해야 합니다.");
    }else{
        console.log("전송");
        // $("#joinForm").submit();
    }

    // let c = true;
    // $.each(results,function(idx,item){
    //     if(!item){
    //         console.log("필수 입력은 다 입력해");
    //         c=false;
    //         return c;
    //     }
    // })  
    // //event 강제 실행
    // if(c){
    //     $("#joinForm").submit();
    // }

})