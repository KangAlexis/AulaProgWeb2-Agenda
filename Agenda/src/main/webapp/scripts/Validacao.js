function validator() {
	/*alert('test')*/
	let nome = frmContato.nome.value
	let telefone = frmContato.telefone.value
	let email = frmContato.email.value

	if (nome === '') {
		alert('Preencha o campo NOME')
		frmContato.nome.focus
		return false
	} else if (telefone === '') {
		alert('Preencha o campo TELEFONE')
		frmContato.telefone.focus
		return false
	}else{
		document.forms["frmContato"].submit()
	}
}