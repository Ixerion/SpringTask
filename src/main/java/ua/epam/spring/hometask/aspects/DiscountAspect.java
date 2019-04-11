package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DiscountAspect {

    public static Map<User, Integer> userDiscountsAccessCounter = new HashMap<>();

    @Pointcut("execution(* *.getDiscount(..))")
    private void getDiscount() {
    }

    @Before("getDiscount()")
    public void discountCounter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];
        if (userDiscountsAccessCounter.containsKey(user))
            userDiscountsAccessCounter.put(user, userDiscountsAccessCounter.get(user) + 1);
        else
            userDiscountsAccessCounter.put(user, 1);
    }
}
