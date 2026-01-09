package pengelolaan_buku_perpustakaan.ui

import pengelolaan_buku_perpustakaan.dao.DashboardDAO
import java.awt.*
import javax.swing.*
import javax.swing.table.DefaultTableModel

class DashboardPanel : JPanel() {

    private val dao = DashboardDAO()
    private val lblTotalBuku = JLabel()
    private val lblDipinjam = JLabel()
    private val lblTotalPinjam = JLabel()
    private val lblDikembalikan = JLabel()

    private val tableModel = DefaultTableModel(
        arrayOf("id_peminjaman", "nama_peminjam", "judul_buku", "tanggal_pinjam", "status"), 0
    )

    init {
        layout = BorderLayout(15, 15)
        background = Color(236, 240, 245)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)

        add(createStatsPanel(), BorderLayout.NORTH)
        add(createTablePanel(), BorderLayout.CENTER)

        loadData()
    }

    private fun loadData() {
        lblTotalBuku.text = dao.totalBuku().toString()
        lblDipinjam.text = dao.bukuDipinjam().toString()
        lblTotalPinjam.text = dao.totalPeminjaman().toString()
        lblDikembalikan.text = dao.dikembalikan().toString()

        tableModel.rowCount = 0
        val rs = dao.peminjamanTerbaru()
        while (rs.next()) {
            tableModel.addRow(arrayOf(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
            ))
        }
        rs.statement.connection.close()
    }

    private fun icon(name: String, size: Int = 22): ImageIcon {
        val url = javaClass.getResource("/icons/$name")
            ?: throw RuntimeException("Icon $name tidak ditemukan")
        val img = ImageIcon(url).image
        return ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH))
    }

    private fun createStatsPanel(): JPanel {
        val panel = JPanel(GridLayout(1, 4, 15, 15))
        panel.isOpaque = false

        panel.add(createCard("Total Buku", lblTotalBuku, "book.png", Color(93, 135, 197)))
        panel.add(createCard("Buku Dipinjam", lblDipinjam, "pinjam.png", Color(243, 156, 18)))
        panel.add(createCard("Total Peminjaman", lblTotalPinjam, "users.png", Color(46, 204, 113)))
        panel.add(createCard("Dikembalikan", lblDikembalikan, "check.png", Color(52, 152, 219)))

        return panel
    }

    private fun createCard(
        title: String,
        value: JLabel,
        iconName: String,
        color: Color
    ): JPanel {

        val panel = JPanel(BorderLayout())
        panel.background = color
        panel.border = BorderFactory.createEmptyBorder(18, 18, 18, 18)

        val lblIcon = JLabel(icon(iconName, 26))

        val lblTitle = JLabel(title)
        lblTitle.foreground = Color.WHITE
        lblTitle.font = Font("Segoe UI", Font.PLAIN, 15)

        value.foreground = Color.WHITE
        value.font = Font("Segoe UI", Font.BOLD, 30)

        val header = JPanel(FlowLayout(FlowLayout.LEFT, 8, 0))
        header.isOpaque = false
        header.add(lblIcon)
        header.add(lblTitle)

        panel.add(header, BorderLayout.NORTH)
        panel.add(value, BorderLayout.CENTER)

        return panel
    }

    private fun createTablePanel(): JPanel {
        val panel = JPanel(BorderLayout(10, 10))
        panel.background = Color.WHITE
        panel.border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        )

        val title = JLabel("Data Peminjaman Terbaru")
        title.font = Font("Segoe UI", Font.BOLD, 16)

        val table = JTable(tableModel)
        table.rowHeight = 28
        table.tableHeader.font = Font("Segoe UI", Font.BOLD, 13)
        table.fillsViewportHeight = true

        panel.add(title, BorderLayout.NORTH)
        panel.add(JScrollPane(table), BorderLayout.CENTER)

        return panel
    }
}
