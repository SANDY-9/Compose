package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

//Compose를 사용하면 Activity가 Android 앱의 진입점으로 유지된다.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Composable 함수의 스타일을 지정한다.
            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {

    // remember 함수는 컴포저블이 컴포지션에 유지되는 동안에만 작동
    // 기기를 회전하면 전체 활동이 다시 시작되므로 모든 상태가 손실 -> 구성이 변경되거나 프로세스가 중단될 때도 발생
    // remember대신 rememberSaveable 사용
    // rememberSaveable : 구성 변경(예: 회전)과 프로세스 중단에도 각 상태를 저장
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier = modifier.fillMaxSize()) {
        if(shouldShowOnboarding) {
            OnboardingScreen(modifier) {
                shouldShowOnboarding = false
                //shouldShowOnboarding을 rember로 기억해 상태변화를 추적하게 했으므로 저장된 false값으로 리컴포지션이 일어난다.
            }
        } else {
            //스크롤이 가능한 열을 표시하기 위해 LazyColumn을 사용한다.
            // LazyColumn은 화면에 보이는 항목만 렌더링하므로 항목이 많은 목록을 렌더링할 때 성능이 향상된다.
            LazyColumn(
                modifier = modifier.padding(vertical = 4.dp)
            ) {
                items(items = names) { name ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)

                    ) {
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Greeting(name)
                        }
                    }
                }
            }
        }
    }
}

// UI에 추가하는 구성요소가 많을수록 생성되는 중첩 레벨이 더 많아진다.
// 함수가 매우 커지면 가독성에 영향을 줄 수 있다.
// 재사용할 수 있는 작은 구성요소를 만들면 앱에서 사용하는 UI 요소의 라이브러리를 쉽게 만들 수 있다. -> 컴포즈 장점
// 각 요소는 화면의 작은 부분을 담당하며 독립적으로 수정할 수 있다.
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    /*
     리컴포지션 : 데이터가 변경되면 Compose는 새 데이터로 이러한 함수를 다시 실행하여 업데이트된 UI를 만든다.
                즉, 함수를 재실행 한다는 것.
     리컴포지션 조건 :
     1.함수의 매개변수가 바뀌어 재호출
     2. remember {
            mutableStateOf(false)
        }로 저장한 변수의 변화
     Composable 함수는 자주 실행될 수 있고 순서와 관계없이 실행될 수 있으므로
     코드가 실행되는 순서 또는 이 함수가 다시 구성되는 횟수에 의존해서는 안된다.
     */

    // 항목이 펼쳐진 상태인지를 저장하는 변수
    // onClick이벤트로 expanded를 변경했을 때 리컴포지션이 일어나지 않음. 함수가 호출될때마다 expanded=false
    // -> Compose에서 추적하고 있지 않아 상태변경으로 감지 하지 않아서
    // var expanded = false

    // Compose에서 상태변경을 추적하게 하기
    // false 값을 가진 변경 가능한 새 상태로 상태를 재설정하여 컴포저블을 다시 호출하는 때는 언제든지 리컴포지션이 일어날 수 있기 때문에
    // 여러 리컴포지션 간에 상태를 유지하려면 remember를 사용하여 변경 가능한 상태를 기억해야 한다.
    /*var expanded = remember {
        //State 및 MutableState: 어떤 값을 보유하고 그 값이 변경될 때마다 UI 업데이트(리컴포지션)를 트리거하는 인터페이스
        mutableStateOf(false)
    }*/
    var expanded by rememberSaveable { mutableStateOf(false) }

    //by animateDpAsState : Dp를 목표값으로 애니메이션 적용
    val extraPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    //Greeting에 다른 배경 색상을 설정 - 우선 Surface로 감싸준다
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(
            horizontal = 8.dp, vertical = 4.dp
        )
    ) {
        //Row : horizontal Linear Layout
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            //Column : vertical Linear Layout
            Column(
                modifier = Modifier
                    .weight(1f)
                    // padding이 0이 되지 않게 해야함, 0 이하면 에러발생할 수 있음
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Black
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}



//미리보기
/*@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)*/
@Preview(
    showBackground = true,
    widthDp = 320,
    name = "GreetingPreviewLight"
)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}