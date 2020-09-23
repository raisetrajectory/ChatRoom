
function confirmCheck()	{

	  if(confirm('本当に削除しますか？')){
	    return true;
	  }else{
	    alert('キャンセルされました。');
	    return false; //追加
	  }

}