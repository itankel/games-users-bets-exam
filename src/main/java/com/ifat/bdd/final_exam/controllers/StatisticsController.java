package com.ifat.bdd.final_exam.controllers;

import com.ifat.bdd.final_exam.model.mapping.EnrichedBetEvents;
import com.ifat.bdd.final_exam.model.mapping.StatisticsData;
import com.ifat.bdd.final_exam.model.mapping.SuspiciousActivity;
import com.ifat.bdd.final_exam.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class StatisticsController {

    @Autowired
    ReportService reportService;

    @GetMapping(value = "suspicious/location/bet-games/{startOn}/{endsOn}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SuspiciousActivity> getSuspiciousUserConnect(@PathVariable String startOn, @PathVariable String endsOn) {
        return reportService.reportOnSuspiciousLocation(Timestamp.valueOf(startOn), Timestamp.valueOf(endsOn));
    }

    @GetMapping(value = "suspicious/activity/bet-games/{startOn}/{endsOn}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnrichedBetEvents> getSuspiciousActivities(@PathVariable String startOn, @PathVariable String endsOn) {
        return reportService.reportOnSuspiciousActivity(Timestamp.valueOf(startOn), Timestamp.valueOf(endsOn));
    }


    @GetMapping(value = "statistics/{gameName}/bet-games/{startOn}/{endsOn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StatisticsData> getSpecificGamesBetsStatisticsInGivenPeriod(@PathVariable String startOn, @PathVariable String endsOn, @PathVariable String gameName) {
        return reportService.reportOnStatisticsPerGroup(Timestamp.valueOf(startOn), Timestamp.valueOf(endsOn), gameName);
    }


    @GetMapping(value = "statistics/all/bet-games/{startOn}/{endsOn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<StatisticsData> getAllGamesBetsStatisticsInGivenPeriod(@PathVariable String startOn, @PathVariable String endsOn) {
        return reportService.reportOnAllStatistics(Timestamp.valueOf(startOn), Timestamp.valueOf(endsOn));
    }


}
