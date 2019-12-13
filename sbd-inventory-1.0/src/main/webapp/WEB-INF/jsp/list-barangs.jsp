<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-barang">Tambah Barang</a>
	</div>
	
	<!-- <div class="search-container">
	    <form action="/search" method="post">
	    	<input type="text" placeholder="Search.." name="merkX">
	      	<input type="text" placeholder="Search.." name="namaBarangX">
	      	<input type="text" placeholder="Search.." name="ruanganX">
	      	<input type="text" placeholder="Search.." name="perolehanX">
	      	<button type="submit"class="btn btn-success">Submit</button>
	    </form>
	</div> -->
	
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Daftar Barang</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="16%">Nama Barang</th>
						<th width="16%">Merk</th>
						<th width="16%">Jumlah Total</th>
						<th width="16%">Jumlah Sisa</th>
						<th width="16%">Kondisi</th>
						<th width="16%">Status Barang</th>
						<th width="16%">Ruangan</th>
						<th width="16%">Sumber Dana</th>
						<th width="16%">Tanggal Maintain</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${barangs}" var="daftarBarang">
						<tr>
							<td>${daftarBarang.namaBarang}</td>
							<td>${daftarBarang.merk}</td>
							<td>${daftarBarang.jumlahTotal}</td>
							<td>${daftarBarang.jumlahSisa}</td>
							<td>${daftarBarang.kondisi}</td>
							<td>${daftarBarang.statusBarang}</td>
							<td>${daftarBarang.namaRuangan}</td>
							<td>${daftarBarang.sumberDana}</td>
							<td><fmt:formatDate value="${daftarBarang.tanggalMaintain}" pattern="dd/MM/yyyy"/></td>
							<td>
								<a type="button" class="btn btn-success" href="/update-barang?idBarang=${daftarBarang.idBarang}">Update</a>
								<a type="button" class="btn btn-warning" href="/delete-barang?idBarang=${daftarBarang.idBarang}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a type="button" class="btn btn-success" href="/download">PDF</a>
		</div>
	</div>

</div>
