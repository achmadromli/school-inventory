<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-barang">Tambah Barang</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>History Tracking</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="16%">Nama Barang</th>
						<th width="16%">Nama Ruangan</th>
						<th width="16%">Jumlah yang Dipindah</th>
						<th width="16%">Keterangan</th>
						<th width="16%">Tanggal History</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${historys}" var="historyBarang">
						<tr>
							<td>${historyBarang.namaBarang}</td>
							<td>${historyBarang.namaRuangan}</td>
							<td>${historyBarang.jumlahPindah}</td>
							<td>${historyBarang.keterangan}</td>
							<td><fmt:formatDate value="${historyBarang.tanggalHistory}" pattern="dd/MM/yyyy hh:mm:ss"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>