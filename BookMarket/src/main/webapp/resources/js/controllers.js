function addToCart(action) {
    document.addForm.action = action;
    document.addForm.submit();
    alert("도서가 장바구니에 추가되었습니다!");
}

function removeFromCart(action) {
	document.removeForm.action = action;
	document.removeForm.submit();
	alert("도서가 장바구니에 삭제되었습니다!");
}

function clearCart() {
	document.clearForm.submit();
	alert("모든 도서가 삭제되었습니다.");
}