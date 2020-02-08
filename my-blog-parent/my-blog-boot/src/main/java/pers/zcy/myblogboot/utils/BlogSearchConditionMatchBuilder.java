package pers.zcy.myblogboot.utils;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@ToString
public class BlogSearchConditionMatchBuilder {
    List<Predicate> predicates;

    public static class Builder{
        private List<Predicate> predicates = new ArrayList<>();

        public Builder titleFuzzyMatch(boolean matchCondition,Predicate predicate){
            if (matchCondition){
                predicates.add(predicate);
            }
            return this;
        }
        public Builder typeMatch(boolean matchCondition,Predicate predicate){
            if (matchCondition){
                predicates.add(predicate);
            }
            return this;
        }
        public Builder isRecommendMatch(boolean matchCondition,Predicate predicate){
            if (matchCondition){
                predicates.add(predicate);
            }
            return this;
        }
        public List<Predicate> conditionsDone(){
            BlogSearchConditionMatchBuilder blogSearchConditionMatchBuilder = new BlogSearchConditionMatchBuilder();
            blogSearchConditionMatchBuilder.predicates=predicates;
            predicates=null;
            return blogSearchConditionMatchBuilder.predicates;
        }
    }
}
