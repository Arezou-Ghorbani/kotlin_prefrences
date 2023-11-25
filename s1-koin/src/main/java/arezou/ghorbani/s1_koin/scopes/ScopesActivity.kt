package arezou.ghorbani.s1_koin.scopes

import arezou.ghorbani.s1_koin.databinding.ActivityScopesBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.scope.activityScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class ScopesActivity : AppCompatActivity(), KoinScopeComponent {
    //Binding
    private lateinit var binding: ActivityScopesBinding

    //Inject
    override val scope: Scope by activityScope()
    private val person: PersonInfo by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScopesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoTxt.text = person.showInfo()
    }
}