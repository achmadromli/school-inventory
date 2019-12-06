<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<fieldset class="form-group">
		<form:label path="cariNamaBarang">Nama Barang</form:label>
		<form:input path="cariNamaBarang" type="text" class="form-control"/>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="cariMerk">Merk</form:label>
		<form:input path="cariMerk" type="text" class="form-control"/>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="cariRuangan">Ruangan</form:label>
		<form:input path="cariRuangan" type="text" class="form-control"/>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="cariPerolehan">Sumber Dana</form:label>
		<form:input path="cariPerolehan" type="text" class="form-control"/>
	</fieldset>
	<div class="col-sm-5">
		<a type="button" class="btn btn-primary" href="/list-barangs">CARI</a>
    </div>
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-barang">Tambah Barang</a>
	</div>
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
						<th width="16%">Jumlah</th>
						<th width="16%">Kondisi</th>
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
							<td>${daftarBarang.jumlah}</td>
							<td>${daftarBarang.kondisi}</td>
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
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>