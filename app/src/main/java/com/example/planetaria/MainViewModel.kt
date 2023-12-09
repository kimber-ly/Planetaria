import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("MyPreferences", android.content.Context.MODE_PRIVATE)

    var isDarkMode: Boolean
        get() = sharedPreferences.getBoolean("darkMode", true)
        set(value) {
            viewModelScope.launch {
                sharedPreferences.edit().putBoolean("darkMode", value).apply()
            }
        }
}
