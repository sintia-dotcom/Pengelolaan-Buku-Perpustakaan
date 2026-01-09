package pengelolaan_buku_perpustakaan.dao

class DashboardDAO {

    fun totalBuku(): Int {
        val conn = KoneksiDB.getConnection()
        val rs = conn.createStatement()
            .executeQuery("SELECT COUNT(*) FROM buku")
        rs.next()
        val result = rs.getInt(1)
        conn.close()
        return result
    }

    fun bukuDipinjam(): Int {
        val conn = KoneksiDB.getConnection()
        val rs = conn.createStatement()
            .executeQuery(
                "SELECT COUNT(*) FROM peminjaman WHERE status = 'Dipinjam'"
            )
        rs.next()
        val result = rs.getInt(1)
        conn.close()
        return result
    }

    fun totalPeminjaman(): Int {
        val conn = KoneksiDB.getConnection()
        val rs = conn.createStatement()
            .executeQuery("SELECT COUNT(*) FROM peminjaman")
        rs.next()
        val result = rs.getInt(1)
        conn.close()
        return result
    }

    fun dikembalikan(): Int {
        val conn = KoneksiDB.getConnection()
        val rs = conn.createStatement()
            .executeQuery(
                "SELECT COUNT(*) FROM peminjaman WHERE status = 'Dikembalikan'"
            )
        rs.next()
        val result = rs.getInt(1)
        conn.close()
        return result
    }

    fun peminjamanTerbaru(): java.sql.ResultSet {
        val conn = KoneksiDB.getConnection()
        val sql = """
            SELECT p.id_peminjaman, p.nama_peminjam, b.judul_buku,
                   p.tanggal_pinjam, p.status
            FROM peminjaman p
            JOIN buku b ON p.id_buku = b.id_buku
            ORDER BY p.tanggal_pinjam DESC
            LIMIT 5
        """
        return conn.prepareStatement(sql).executeQuery()
    }
}
