function nullCheck(data, dest, kind){
    if(data == null || data ==''){
        $(dest).html(kind+" 는 필수입니다.");
        return false;
    }else{
        $(dest).html("정상입니다.");
        return true;
    }
}

function equals (data,checkData){
    if(data == checkData){
        return true;
    }else{
        return false;
    }
}