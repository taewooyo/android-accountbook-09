package com.woowa.accountbook.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.common.rawToMoneyFormat
import com.woowa.accountbook.ui.iconpack.IconPack
import com.woowa.accountbook.ui.iconpack.Plus
import com.woowa.accountbook.ui.theme.*

@Composable
fun AccountBookFloatingButton(
    icon: ImageVector,
    onClicked: () -> Unit,
) {
    FloatingActionButton(
        backgroundColor = Yellow,
        contentColor = White,
        onClick = onClicked
    ) {
        Icon(imageVector = icon, contentDescription = "plus_floating_button")
    }
}

@Composable
fun AccountBookButton(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    onClicked: () -> Unit,
    isClick: Boolean,
    enabled: Boolean = true,
    enabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    clickBackgroundColor: Color,
    unClickBackgroundColor: Color,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClicked,
        enabled = enabled,
        shape = shape,
        colors = if (isClick) {
            ButtonDefaults.buttonColors(
                backgroundColor = clickBackgroundColor,
                disabledBackgroundColor = enabledBackgroundColor
                    .compositeOver(White),
            )
        } else {
            ButtonDefaults.buttonColors(
                backgroundColor = unClickBackgroundColor,
                disabledBackgroundColor = enabledBackgroundColor
                    .compositeOver(White),
            )
        },
        content = content
    )
}

@Composable
fun LeftCornerCheckButton(
    checkBox: Boolean,
    checked: Boolean,
    onClicked: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    checkedColor: Color,
    enabled: Boolean = true,
    disabledColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color,
    labelText: String = "",
    labelPriceText: String = "",
) {
    AccountBookButton(
        modifier = Modifier
            .width(200.dp),
        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
        enabled = enabled,
        enabledBackgroundColor = LightPurple.copy(alpha = 0.5f),
        onClicked = { onClicked() },
        isClick = checked,
        clickBackgroundColor = Purple,
        unClickBackgroundColor = LightPurple
    ) {
        if (checkBox) {
            AccountBookCheckBox(
                checked = checked,
                enabled = enabled,
                onCheckedChange = { onCheckedChange(it) },
                disabledColor = disabledColor,
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor,
                checkmarkColor = checkmarkColor
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(
            text = labelText,
            style = Typography.overline,
            color = White
        )
        if (checkBox) {
            Text(
                text = labelPriceText,
                style = Typography.overline,
                color = White
            )
        }
    }
}

@Composable
fun RightCornerCheckButton(
    checkBox: Boolean,
    checked: Boolean,
    onClicked: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    checkedColor: Color,
    enabled: Boolean = true,
    disabledColor: Color,
    uncheckedColor: Color,
    checkmarkColor: Color,
    labelText: String = "",
    labelPriceText: String = ""
) {
    AccountBookButton(
        modifier = Modifier
            .width(200.dp),
        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
        enabled = enabled,
        enabledBackgroundColor = LightPurple.copy(alpha = 0.5f),
        onClicked = { onClicked() },
        isClick = checked,
        clickBackgroundColor = Purple,
        unClickBackgroundColor = LightPurple
    ) {
        if (checkBox) {
            AccountBookCheckBox(
                checked = checked,
                enabled = enabled,
                onCheckedChange = { onCheckedChange(it) },
                disabledColor = disabledColor,
                checkedColor = checkedColor,
                uncheckedColor = uncheckedColor,
                checkmarkColor = checkmarkColor
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(
            text = labelText,
            style = Typography.overline,
            color = White
        )
        if (checkBox) {
            Text(
                text = labelPriceText,
                style = Typography.overline,
                color = White
            )
        }
    }
}

@Composable
fun FilterCheckBoxButton(
    checkbox: Boolean = true,
    totalIncome: Int,
    totalExpense: Int,
    inComeIsChecked: MutableState<Boolean>,
    expenseIsChecked: MutableState<Boolean>,
    enabled: Boolean,
    onIncomeCheckBoxClicked: (Boolean) -> Unit,
    onExpenseCheckBoxClicked: (Boolean) -> Unit,
    onIncomeButtonClicked: () -> Unit,
    onExpenseButtonClicked: () -> Unit,
    onIncomeClicked: () -> Unit,
    onExpenseClicked: () -> Unit,
    onBothClicked: () -> Unit,
    onEmptyClicked: () -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        LeftCornerCheckButton(
            enabled = enabled,
            checkBox = checkbox,
            checked = inComeIsChecked.value,
            onClicked = {
                onIncomeButtonClicked()
                onClickFilterButton(
                    inComeIsChecked,
                    expenseIsChecked,
                    onBothClicked,
                    onIncomeClicked,
                    onExpenseClicked,
                    onEmptyClicked
                )
            },
            onCheckedChange = {
                onIncomeCheckBoxClicked(it)
                onClickFilterButton(
                    inComeIsChecked,
                    expenseIsChecked,
                    onBothClicked,
                    onIncomeClicked,
                    onExpenseClicked,
                    onEmptyClicked
                )
            },
            disabledColor = White,
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "수입",
            labelPriceText = rawToMoneyFormat(totalIncome, 1)
        )

        RightCornerCheckButton(
            enabled = enabled,
            checkBox = checkbox,
            checked = expenseIsChecked.value,
            onClicked = {
                onExpenseButtonClicked()
                onClickFilterButton(
                    inComeIsChecked,
                    expenseIsChecked,
                    onBothClicked,
                    onIncomeClicked,
                    onExpenseClicked,
                    onEmptyClicked
                )
            },
            onCheckedChange = {
                onExpenseCheckBoxClicked(it)
                onClickFilterButton(
                    inComeIsChecked,
                    expenseIsChecked,
                    onBothClicked,
                    onIncomeClicked,
                    onExpenseClicked,
                    onEmptyClicked
                )
            },
            disabledColor = White,
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "지출",
            labelPriceText = rawToMoneyFormat(totalExpense, 0)
        )
    }
}

@Composable
fun TextButton(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    onClicked: () -> Unit,
    isClick: Boolean,
    enabled: Boolean = true,
    enabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    clickBackgroundColor: Color,
    unClickBackgroundColor: Color,
) {
    AccountBookButton(
        modifier = modifier,
        shape = shape,
        onClicked = onClicked,
        enabled = enabled,
        enabledBackgroundColor = enabledBackgroundColor,
        isClick = isClick,
        clickBackgroundColor = clickBackgroundColor,
        unClickBackgroundColor = unClickBackgroundColor
    ) {
        Text(
            text = text,
            style = Typography.button,
            color = textColor
        )
    }
}

fun onClickFilterButton(
    inComeIsChecked: MutableState<Boolean>,
    expenseIsChecked: MutableState<Boolean>,
    onBothClicked: () -> Unit,
    onIncomeClicked: () -> Unit,
    onExpenseClicked: () -> Unit,
    onEmptyClicked: () -> Unit
) {
    if (inComeIsChecked.value && expenseIsChecked.value) onBothClicked()
    else if (inComeIsChecked.value) onIncomeClicked()
    else if (expenseIsChecked.value) onExpenseClicked()
    else onEmptyClicked()
}

@Preview(showBackground = false)
@Composable
fun AccountBookFloatingButtonPreview() {
    AccountBookFloatingButton(
        icon = IconPack.Plus,
        onClicked = {}
    )
}

@Preview(showBackground = false)
@Composable
fun LeftCornerLabelCheckBoxPreview() {
    LeftCornerCheckButton(
        checkBox = true,
        checked = true,
        onClicked = {},
        onCheckedChange = { },
        disabledColor = White,
        checkedColor = White,
        uncheckedColor = White,
        checkmarkColor = Purple,
        labelText = "수입",
        labelPriceText = "1,822,480"
    )
}

@Preview(showBackground = false)
@Composable
fun LeftCornerLabelUnCheckBoxPreview() {
    LeftCornerCheckButton(
        checkBox = true,
        checked = false,
        disabledColor = White,
        onClicked = {},
        onCheckedChange = { },
        checkedColor = White,
        uncheckedColor = White,
        checkmarkColor = Purple,
        labelText = "수입",
        labelPriceText = "1,822,480"
    )
}


@Preview(showBackground = false)
@Composable
fun RightCornerLabelCheckBoxPreview() {
    RightCornerCheckButton(
        checkBox = true,
        checked = true,
        onClicked = {},
        disabledColor = White,
        onCheckedChange = { },
        checkedColor = White,
        uncheckedColor = White,
        checkmarkColor = Purple,
        labelText = "지출",
        labelPriceText = "798,180"
    )
}

@Preview(showBackground = false)
@Composable
fun RightCornerLabelUnCheckBoxPreview() {
    RightCornerCheckButton(
        checkBox = true,
        checked = false,
        onClicked = {},
        disabledColor = White,
        onCheckedChange = { },
        checkedColor = White,
        uncheckedColor = White,
        checkmarkColor = Purple,
        labelText = "지출",
        labelPriceText = "798,180"
    )
}

@Preview(showBackground = false)
@Composable
fun AccountBoxIncomeAndExpense() {
    Row() {
        LeftCornerCheckButton(
            checkBox = true,
            checked = true,
            disabledColor = White,
            onClicked = {},
            onCheckedChange = { },
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "수입",
            labelPriceText = "1,822,480"
        )

        RightCornerCheckButton(
            checkBox = true,
            checked = false,
            onClicked = {},
            onCheckedChange = { },
            disabledColor = White,
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "지출",
            labelPriceText = "798,180"
        )
    }
}

@Preview(showBackground = false)
@Composable
fun AccountBoxDisabledIncomeAndExpense() {
    Row() {
        LeftCornerCheckButton(
            checkBox = true,
            checked = true,
            enabled = false,
            onClicked = {},
            disabledColor = White,
            onCheckedChange = { },
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "수입",
            labelPriceText = "1,822,480"
        )

        RightCornerCheckButton(
            checkBox = true,
            checked = false,
            enabled = false,
            onClicked = {},
            disabledColor = White,
            onCheckedChange = { },
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "지출",
            labelPriceText = "798,180"
        )
    }
}

@Preview(showBackground = false)
@Composable
fun AccountBoxIncomeAndExpense2() {
    Row() {
        LeftCornerCheckButton(
            checkBox = false,
            checked = true,
            onClicked = {},
            onCheckedChange = { },
            disabledColor = White,
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "수입",
            labelPriceText = "1,822,480"
        )

        RightCornerCheckButton(
            checkBox = false,
            checked = false,
            onClicked = {},
            onCheckedChange = { },
            disabledColor = White,
            checkedColor = White,
            uncheckedColor = White,
            checkmarkColor = Purple,
            labelText = "지출",
            labelPriceText = "798,180"
        )
    }
}

@Preview(showBackground = false)
@Composable
fun AccountBoxRegistrationEnabledButton() {
    TextButton(
        text = "등록하기",
        textColor = White,
        modifier = Modifier
            .width(328.dp)
            .height(50.dp),
        shape = RoundedCornerShape(14.dp),
        onClicked = { },
        isClick = true,
        enabledBackgroundColor = Yellow.copy(alpha = 0.5f),
        clickBackgroundColor = Yellow,
        unClickBackgroundColor = Yellow
    )
}

@Preview(showBackground = false)
@Composable
fun AccountBoxRegistrationDisabledButton() {
    TextButton(
        text = "등록하기",
        textColor = White,
        modifier = Modifier
            .width(328.dp)
            .height(50.dp),
        shape = RoundedCornerShape(14.dp),
        onClicked = { },
        isClick = true,
        enabled = false,
        enabledBackgroundColor = Yellow.copy(alpha = 0.5f),
        clickBackgroundColor = Yellow,
        unClickBackgroundColor = Yellow
    )
}

@Preview(showBackground = false)
@Composable
fun AccountBoxUpdateEnabledButton() {
    TextButton(
        text = "수정하기",
        textColor = White,
        modifier = Modifier
            .width(328.dp)
            .height(50.dp),
        shape = RoundedCornerShape(14.dp),
        onClicked = { },
        isClick = true,
        enabledBackgroundColor = Yellow.copy(alpha = 0.5f),
        clickBackgroundColor = Yellow,
        unClickBackgroundColor = Yellow
    )
}

@Preview(showBackground = false)
@Composable
fun AccountBoxUpdateDisabledButton() {
    TextButton(
        text = "수정하기",
        textColor = White,
        modifier = Modifier
            .width(328.dp)
            .height(50.dp),
        shape = RoundedCornerShape(14.dp),
        onClicked = { },
        isClick = true,
        enabled = false,
        enabledBackgroundColor = Yellow.copy(alpha = 0.5f),
        clickBackgroundColor = Yellow,
        unClickBackgroundColor = Yellow
    )
}