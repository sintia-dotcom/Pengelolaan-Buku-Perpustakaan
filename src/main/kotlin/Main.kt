package pengelolaan_buku_perpustakaan

import com.formdev.flatlaf.FlatLightLaf
import pengelolaan_buku_perpustakaan.ui.LoginFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager

fun main() {
    try {
        println("MAIN JALAN")

        UIManager.setLookAndFeel(FlatLightLaf())

        SwingUtilities.invokeLater {
            println("BUKA LOGIN")
            LoginFrame()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
