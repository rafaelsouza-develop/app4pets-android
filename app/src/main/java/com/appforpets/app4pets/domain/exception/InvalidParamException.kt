package com.appforpets.app4pets.domain.exception

import java.lang.Exception

open class InvalidParamException(val param: String, message: String? = null): Exception(message)