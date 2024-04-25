package com.xdisx.contract.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ContractApi {
    String ROOT_PATH = "/xdisx";

    @GetMapping(path = ROOT_PATH +  "/salut")
    String salut(@RequestParam("numar") Integer numar);
}
