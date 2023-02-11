<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Family Car</title>
<link rel="stylesheet"
	href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script src='https://unpkg.com/axios/dist/axios.min.js'></script>
<style>
.tui-grid-cell-content{
	text-align: center;
}
#save1 > input{
	color: red;
}
#save2 > input{
	color: blue;
}
#save3 > input{
	color: green;
}
</style>
</head>
<script type="text/javascript">
let grid = {};
document.addEventListener("DOMContentLoaded", function(){
	setGrid();
	setAction();
});

const setGrid = () => {
	const Grid = tui.Grid;
	const dataSource = {
		contentType: 'application/json',
		api: {
			readData: {
				url: '/familyCar/listAll',
				method: 'GET'
			}
		}
	};
	
	grid = new Grid({
		el: document.getElementById('grid'), // Container element
		columns: [
			{
			  header: 'Id',
			  name: 'id'
			},
			{
			  header: '타입',
			  name: 'type'
			},
			{
			  header: '신청시작일',
			  name: 'stdDate'
			},
			{
			  header: '신청종료일',
			  name: 'endDate'
			},
			{
			  header: '요청자',
			  name: 'provided'
			},
			{
			  header: '저장일',
			  name: 'cdate'
			}
		],
		bodyHeight: 300,
		data: dataSource,
	});
	Grid.applyTheme('striped'); // Call API of static method
}

const setAction = () => {
	const searchBtn = document.getElementById('search');
	searchBtn.addEventListener("click", () => {
		grid.readData(1);
	});
	const saveOneBtn = document.getElementById('saveOneBtn');
	saveOneBtn.addEventListener("click", () => {
		saveOne();
	});
	const deleteAllBtn = document.getElementById('deleteAllBtn');
	deleteAllBtn.addEventListener("click", () => {
		deleteAll();
	});
}

const saveOne = () => {
	const saveInputs = document.querySelectorAll("#save1 > input");
	const familyCar = {};
	for(let i=0; i<saveInputs.length; i++){
		const input = saveInputs[i];
		familyCar[input.name] = input.value;
	}
	console.log({familyCar});
	
	axios({
		method: "POST",
		url: "/familyCar",
		data: familyCar,
	})
	.then(data => grid.readData(1))
	.catch(err => console.log("시스템 에러 : " + err.response.data.message));
	
}

const deleteAll = () => {
	axios({
		method: "POST",
		url: "/familyCar/deleteAll",
	})
	.then(data => grid.readData(1))
	.catch(err => console.log("시스템 에러 : " + err.response.data.message));
	
}

</script>
<body>
	<button id="search">조회</button>
	<div id="grid"></div>
	<div><h2>저장 테스트</h2></div>
	<div id="save1">
		<input type="text" id="type" name="type" value="승합차" />
		<input type="text" id="stdDate" name="stdDate" value="2023-02-20" />
		<input type="text" id="endDate" name="endDate" value="2023-02-20" />
		<input type="text" id="provided" name="provided" value="요청자1" />
	</div>
	<br/>
	<button id="saveOneBtn">저장</button>
	<button id="deleteAllBtn">초기화</button>
</body>
</html>